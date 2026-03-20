#!/usr/bin/env node
/**
 * svg-clean.js
 * Reusable CLI to clean SVGs:
 * 1) Run SVGO (multipass, pretty)
 * 2) Inject default font CSS for text/tspan and strip redundant font attrs
 * 3) Inject common path CSS classes and refactor matching paths
 * 4) Remove unreferenced ids (keep marker/filter/clipPath refs and top-level semantic id if provided)
 */
const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

function run(cmd, cwd) {
  return execSync(cmd, { cwd, stdio: 'pipe' }).toString();
}

function injectStyleDefaults(svg) {
  return svg.replace(/<style>([\s\S]*?)<\/style>/, (m, css) => {
    const defaults = "\ntext, tspan { font-family: 'Noto Sans Display', 'Noto Sans', 'Helvetica Neue', Arial, sans-serif; font-weight: 400; }\n";
    if (css.includes('text, tspan {')) return `<style>${css}</style>`;
    return `<style>${css}${defaults}</style>`;
  });
}

function stripRedundantFontAttrs(svg) {
  return svg
    .replace(/\sfont-family=\"Noto Sans Display\"/g, '')
    .replace(/\sfont-weight=\"400\"/g, '');
}

function injectPathClasses(svg) {
  return svg.replace(/<style>([\s\S]*?)<\/style>/, (m, css) => {
    const classes = `\n/* common path styles */\n.line { stroke:#000; stroke-linecap:round; stroke-linejoin:round; stroke-width:3; }\n.arrow-line { stroke:#000; stroke-linecap:round; stroke-linejoin:round; stroke-width:3; marker-end:url(#FilledArrow_Marker); }\n.outline-4 { stroke:#344463; stroke-linecap:round; stroke-linejoin:round; stroke-width:4; }\n`;
    if (css.includes('.arrow-line') || css.includes('.line')) return `<style>${css}</style>`;
    return `<style>${css}${classes}</style>`;
  });
}

function addClassToPathTag(tag, className) {
  if (/\sclass=\"[^\"]*\"/.test(tag)) {
    return tag.replace(/class=\"([^\"]*)\"/, (m, cls) => `class="${cls.includes(className) ? cls : (cls + ' ' + className).trim()}"`);
  }
  return tag.replace(/<path(\s)/, `<path class=\"${className}\"$1`);
}

function refactorPaths(svg) {
  svg = svg.replace(/<path([^>]*)>/g, (full, attrs) => {
    const hasStrokeBlack = /\sstroke=\"#000\"/.test(attrs);
    const hasLCap = /\sstroke-linecap=\"round\"/.test(attrs);
    const hasLJoin = /\sstroke-linejoin=\"round\"/.test(attrs);
    const hasW3 = /\sstroke-width=\"3\"/.test(attrs);
    const hasMarker = /\smarker-end=\"url\(#FilledArrow_Marker\)\"/.test(attrs);
    if (hasStrokeBlack && hasLCap && hasLJoin && hasW3) {
      let tag = `<path${attrs}>`;
      tag = tag
        .replace(/\sstroke=\"#000\"/g, '')
        .replace(/\sstroke-linecap=\"round\"/g, '')
        .replace(/\sstroke-linejoin=\"round\"/g, '')
        .replace(/\sstroke-width=\"3\"/g, '');
      if (hasMarker) {
        tag = tag.replace(/\smarker-end=\"url\(#FilledArrow_Marker\)\"/g, '');
        tag = addClassToPathTag(tag, 'arrow-line');
      } else {
        tag = addClassToPathTag(tag, 'line');
      }
      return tag;
    }
    return full;
  });
  svg = svg.replace(/<path([^>]*)>/g, (full, attrs) => {
    const hasStroke = /\sstroke=\"#344463\"/.test(attrs);
    const hasLCap = /\sstroke-linecap=\"round\"/.test(attrs);
    const hasLJoin = /\sstroke-linejoin=\"round\"/.test(attrs);
    const hasW4 = /\sstroke-width=\"4\"/.test(attrs);
    if (hasStroke && hasLCap && hasLJoin && hasW4) {
      let tag = `<path${attrs}>`;
      tag = tag
        .replace(/\sstroke=\"#344463\"/g, '')
        .replace(/\sstroke-linecap=\"round\"/g, '')
        .replace(/\sstroke-linejoin=\"round\"/g, '')
        .replace(/\sstroke-width=\"4\"/g, '');
      tag = addClassToPathTag(tag, 'outline-4');
      return tag;
    }
    return full;
  });
  return svg;
}

function removeUnusedIds(svg, semanticIdHint) {
  const refs = new Set();
  const urlRefRe = /url\(#([^)]+)\)/g;
  const hrefRe = /(xlink:href|href)="#([^"]+)"/g;
  let m;
  while ((m = urlRefRe.exec(svg)) !== null) refs.add(m[1]);
  while ((m = hrefRe.exec(svg)) !== null) refs.add(m[2]);
  const keep = new Set(refs);
  if (semanticIdHint) keep.add(semanticIdHint);
  // preserve some common ids if present
  ['FilledArrow_Marker','Shadow','clip_path','clip_path_2'].forEach(id => keep.add(id));
  return svg.replace(/\s(id)="([^"]+)"/g, (full, attr, id) => keep.has(id) ? full : '');
}

function cleanSvg(inputPath, opts = {}) {
  const cwd = path.dirname(inputPath);
  const base = path.basename(inputPath);
  const tmp1 = base.replace(/\.svg$/i, '.optimized.svg');
  const tmp2 = base.replace(/\.svg$/i, '.optimized.font-clean.svg');
  const tmp3 = base.replace(/\.svg$/i, '.optimized.styled.svg');
  const out = base.replace(/\.svg$/i, '.optimized.styled.noids.svg');

  // 1) SVGO
  run(`npx --yes svgo --multipass --pretty -i ${JSON.stringify(base)} -o ${JSON.stringify(tmp1)}`, cwd);

  // 2) Fonts
  let s = fs.readFileSync(path.join(cwd, tmp1), 'utf8');
  s = injectStyleDefaults(s);
  s = stripRedundantFontAttrs(s);
  fs.writeFileSync(path.join(cwd, tmp2), s);

  // 3) Path classes
  s = injectPathClasses(s);
  s = refactorPaths(s);
  fs.writeFileSync(path.join(cwd, tmp3), s);

  // 4) Remove unused ids
  s = removeUnusedIds(s, opts.semanticIdHint);
  fs.writeFileSync(path.join(cwd, out), s);

  // Final optimize
  run(`npx --yes svgo --multipass --pretty -i ${JSON.stringify(out)} -o ${JSON.stringify(out)}`, cwd);
  return path.join(cwd, out);
}

function main() {
  const argv = process.argv.slice(2);
  if (argv.length < 1) {
    console.error('Usage: svg-clean.js <input.svg> [--semantic-id <id>]');
    process.exit(1);
  }
  const input = path.resolve(argv[0]);
  let semanticIdHint;
  for (let i=1; i<argv.length; i++) {
    if (argv[i] === '--semantic-id' && argv[i+1]) { semanticIdHint = argv[i+1]; i++; }
  }
  const out = cleanSvg(input, { semanticIdHint });
  console.log('Output:', out);
}

if (require.main === module) main();
