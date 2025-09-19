#!/usr/bin/env python3
"""
Script to update SVG files by multiplying width and height attributes by 3.
This script will:
1. Find all SVG files in ds-architectures/images subfolders
2. Parse each SVG file to find width and height attributes
3. Multiply the values by 3
4. Update the files with the new dimensions
"""

import os
import re
import glob
from pathlib import Path

multiplier = 2
lecture = "prog-einfuehrung"
images_folder = "images"
width_pattern = r'\s{0,4}width="(\d+(?:\.\d+)?)"'
height_pattern = r'\s{0,4}height="(\d+(?:\.\d+)?)"'

def update_svg_dimensions(svg_file_path, multiplier):
    """Update width and height attributes in an SVG file by multiplying by the given multiplier."""
    try:
        with open(svg_file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content

        def multiply_width(match):
            value = float(match.group(1))
            new_value = int(value * multiplier) if value.is_integer() else value * multiplier
            return f'width="{new_value}"'
        
        def multiply_height(match):
            value = float(match.group(1))
            new_value = int(value * multiplier) if value.is_integer() else value * multiplier
            return f'height="{new_value}"'
        
        # Update width and height attributes
        content = re.sub(width_pattern, multiply_width, content,1)
        content = re.sub(height_pattern, multiply_height, content,1)
        
        # Only write if content changed
        if content != original_content:
            with open(svg_file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            return True
        else:
            return False
            
    except Exception as e:
        print(f"Error updating {svg_file_path}: {e}")
        return False

def find_svg_files(root_dir):
    """Find all SVG files in the directory."""
    pattern = os.path.join(root_dir, lecture, images_folder, "**", "*.svg")
    return glob.glob(pattern, recursive=True)

def main():
    root_dir = "/Users/Michael/Teaching/Lectures"
    
    print("Finding SVG files ...")
    svg_files = find_svg_files(root_dir)
    print(f"Found {len(svg_files)} SVG files")
    
    if not svg_files:
        print("No SVG files found.")
        return
    
    updated_count = 0
    failed_count = 0
    
    print(f"\nUpdating SVG dimensions (multiplying by 3)...")
    for svg_file in svg_files:
        print(f"Processing: {os.path.relpath(svg_file, root_dir)}")
        
        # Show current dimensions before update
        try:
            with open(svg_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            width_match = re.search(width_pattern, content)
            height_match = re.search(height_pattern, content)
            
            if width_match and height_match:
                old_width = width_match.group(1)
                old_height = height_match.group(1)
                print(f"  Current: width={old_width}, height={old_height}")
            else:
                print("  No width/height attributes found")
                
        except Exception as e:
            print(f"  Error reading file: {e}")
        
        if update_svg_dimensions(svg_file, multiplier):
            updated_count += 1
            print(f"  ✓ Updated successfully")
            
            # Show new dimensions
            try:
                with open(svg_file, 'r', encoding='utf-8') as f:
                    content = f.read()
                
                width_match = re.search(width_pattern, content)
                height_match = re.search(height_pattern, content)
                
                if width_match and height_match:
                    new_width = width_match.group(1)
                    new_height = height_match.group(1)
                    print(f"  New: width={new_width}, height={new_height}")
                    
            except Exception as e:
                print(f"  Error reading updated file: {e}")
        else:
            failed_count += 1
            print(f"  ✗ Failed to update")
        
        print()
    
    print(f"Update complete:")
    print(f"  Successfully updated: {updated_count}")
    print(f"  Failed updates: {failed_count}")

if __name__ == "__main__":
    main()
