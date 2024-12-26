import os

def list_mp3_files(directory, output_file):
    try:
        with open(output_file, 'w', encoding='utf-8') as file:  # Specify UTF-8 encoding
            for root, _, files in os.walk(directory):
                for name in files:
                    if name.endswith('.mp3'):
                        file_path = os.path.join(root, name)
                        file.write(f"{name};{file_path}\n")
        print(f"MP3 file list successfully written to {output_file}")
    except Exception as e:
        print(f"An error occurred: {e}")

# Specify the directory and output file here
directory = input("Enter the directory to search for MP3 files: ")
output_file = input("Enter the output file path (e.g., mp3_list.txt): ")

list_mp3_files(directory, output_file)
