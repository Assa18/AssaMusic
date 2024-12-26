def modify_lines(input_file, output_file):
    try:
        with open(input_file, 'r', encoding='utf-8') as infile, open(output_file, 'w', encoding='utf-8') as outfile:
            for line in infile:
                # Strip trailing newline or spaces
                line = line.strip()
                
                # Split at the first ';'
                parts = line.split(';', 1)
                
                if len(parts) > 1:
                    first_part = parts[0]
                    remaining = parts[1]
                    
                    # Split the first part using " - " as the delimiter
                    subparts = first_part.split(' - ', 1)
                    
                    if len(subparts) == 2:
                        first_subpart = subparts[0]
                        second_subpart = subparts[1].rstrip('.mp3')
                        
                        # Reverse and rejoin the two parts
                        modified_first_part = f"{second_subpart};{first_subpart}"
                        
                        # Reconstruct the line
                        modified_line = f"{modified_first_part};{remaining}\n"
                    else:
                        # If " - " not found, keep the original line
                        modified_line = line + '\n'
                else:
                    # If ';' not found, keep the original line
                    modified_line = line + '\n'
                
                # Write the modified line
                outfile.write(modified_line)
        print(f"Modified lines successfully written to {output_file}")
    except Exception as e:
        print(f"An error occurred: {e}")

# Example usage
input_file = input("Enter the input file path: ")
output_file = input("Enter the output file path: ")

modify_lines(input_file, output_file)
