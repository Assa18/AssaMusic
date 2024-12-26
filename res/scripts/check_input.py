# Script to find line numbers of lines without at least two semicolons

def find_lines_without_two_semicolons(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            for line_number, line in enumerate(file, start=1):
                if line.count(';') < 2:
                    print(f"Line {line_number}: {line.strip()}")
    except FileNotFoundError:
        print(f"Error: The file '{file_path}' was not found.")
    except Exception as e:
        print(f"An unexpected error occurred: {e}")

# Usage example:
# Replace 'your_file.txt' with the path to the file you want to check.
if __name__ == "__main__":
    file_path = input("Enter the path to the file: ")
    find_lines_without_two_semicolons(file_path)
