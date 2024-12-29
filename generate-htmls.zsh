#!/bin/zsh 

# The following script checks every couple of seconds if an rst file was updated.
# If so, it updates the corresponding html file.
# It also processes all .publish files in the current directory and copies the 
# files listed in them to the specified target directory (see below).

target_directory="/Users/Michael/Sites/delors.github.io/" # must end with "/"!
wd=$(pwd)

# Use the following lines to enable/disable debug output:
# set -x
# set +x

function update_html_if_necessary() {
    html_file="$1.html"   
    language_option=""
    if [[ "$1" == *"."*".rst" ]]
    then
        lang=$(\
            echo "$1" |\
            grep -Eo '\.([a-z]{2,3})\.rst$' |\
            grep -Eo "[a-z]+\." |\
            sed -E 's/\.//g')
        language_option="--language=$lang"
    fi
    if [[ ! -f "$html_file" || "$html_file" -ot "$1" ]]
    then
        path_prefix=$(echo "$1" | sed -E 's/[.0-9a-zA-Z_-]+/../g' | grep -Eo "^(\.+/)+")
        if [ $? -ne 0 ]
        then
            path_prefix=""
        fi
        echo "$(date '+%Y-%m-%d %H:%M:%S') updating:" $html_file 
        reStructuredTextToLectureDoc2/rst2ld.py "$1" \
            --output "$html_file" \
            --ld-path $path_prefix"LectureDoc2" \
            --ld-exercises-passwords "$html_file.passwords.txt" \
            --link-stylesheet \
            $language_option
            
    fi
}

function check_all_rst_files() {
    for f in **/*.rst # [0-9]*.rst
    do
        update_html_if_necessary "$f"
    done
}

function process_all_publish_files_in_subfolders() {
    for f in */.publish
    do
        path_to_publish=$(dirname "$f")
        target_folder="$target_directory$path_to_publish"
        if [ -d "$target_folder" ]
        then
            cd "$target_folder"
            all_files=$(find * -type f | grep -E -v "/$" )
            cd "$wd"
            removed_files=$(echo -n "$all_files" | grep -F -v -x -f "$f")
            if [ $? -eq 2 ]
            then
                echo "Fatal error[" $? "] while processing:" $f
                return
            fi
            echo -n "$removed_files" | while IFS= read -r removed_file
            do
                target_file="$target_directory$path_to_publish/$removed_file"
                echo "$(date '+%Y-%m-%d %H:%M:%S') removing file:" $target_file
                rm $target_file
            done
        fi
        #set -x
        rsync -a "$path_to_publish" --files-from="$f" "$target_directory$path_to_publish/" 
        #set +x
    done
}   

function process_publish_file_in_root_folder() {
    f=".publish"
    path_to_publish=$(dirname "$f")
    removed_files=$(ls -p "$target_directory" | grep -v "/" | grep -F -v -x -f "$f")
    if [ $? -eq 2 ]; then
        echo "Fatal error [" $? "] while processing" $f "in" $(pwd)
        return
    fi
    echo -n "$removed_files" | while IFS= read -r removed_file
    do
        target_file="$target_directory$removed_file"
        echo "$(date '+%Y-%m-%d %H:%M:%S') removing file in root folder:" $target_file
        rm $target_file
    done
    #set -x
    rsync -a "$path_to_publish" --files-from="$f" "$target_directory$path_to_publish/"
    #set +x
}   

function remove_removed_folders() {
    # We first need to find the folders that are removed in the local directory.
    # For that, we simply list the folder in the remote directory and the local 
    # directory and then compare the two lists by sorting and counting the
    # entries. The entries that are only present in the target directory are
    # the ones which only exist once (the count is 1). These are the folders
    # that will be removed.
    # We exclude the .git folder and the W3M20014 folder from the list of folders!
    remote_folders=$(cd "$target_directory" && find * -type d -not -ipath "*/.git/*" -not -ipath "*/W3M20014/*" && cd $wd)
    # only top level folders: local_folders=$(ls -p . | grep "/" | sort)
    local_folders=$(find * -type d -not -ipath "*/.git/*")
    shared_folders=$(echo $remote_folders"\n"$local_folders | sort | uniq -c | grep -E "^\s*2 " | sed -E "s/^ *2 //")
    removed_folders=$(echo $remote_folders"\n"$shared_folders | sort | uniq -c | grep -E "^\s*1 " | sed -E "s/^ *1 //" | grep -v "W3M20014")
    if [[ "$removed_folders" == "" ]]; then 
        # If we don't return now, we will delete the target folder itself!
        return; 
    fi
    echo "$removed_folders" | while IFS= read -r removed_folder
    do
        target_folder="$target_directory$removed_folder"
        echo "$(date '+%Y-%m-%d %H:%M:%S') removing folder:" $target_folder
        rm -r $target_folder
    done
}

echo "Checks every couple of seconds if an rst file was updated."
echo "Press CTRL+C to terminate."
while true
do
    check_all_rst_files
    cd $wd # Just to be sure that we are (still) in the right directory
    process_publish_file_in_root_folder
    cd $wd # Just to be sure that we are (still) in the right directory
    process_all_publish_files_in_subfolders
    cd $wd # Just to be sure that we are (still) in the right directory
    remove_removed_folders

    sleep 3
    # echo "check done:"$(date '+%Y-%m-%d %H:%M:%S')
done
