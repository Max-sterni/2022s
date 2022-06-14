Requirements:
=============
- java-17


Example usage for sheet_00, user csxx0000, and group 8:
=======================================================

- Extract the compressed folder "submission-check.zip" (available via OLAT)
- Compress the folder "at" as .zip archive and name the compressed folder "submission.zip"
- Adjust the provided config-file "userConfig.txt" by setting your group-number (as int; leading zero is added by the script) and your zidUsername
- Run the following command: 
	java -jar submission-check.jar userConfig.txt requiredFilesConfig.txt submission.zip


General usage:
==============

java -jar submission-check.jar <userConfigPath> <requiredFilesPath> <compressedFolderSubmission>

- <userConfigFile> 			=> path to user-config-file
- <requiredFilesFile> 			=> path to required-files-file
- <compressedpFolderSubmission> 	=> path to compressed folder (must be either .zip or .tar.gz)


UserConfigFile-Content:
-----------------------

zidUsername=<your-zid-user>
gXX=<group-nr-as-int>


Example:

zidUsername=csxx0000
gXX=8


RequiredFilesFile-Content:
--------------------------

<list-of-required-files>

Example:

at/ac/uibk/pm/gXX/zidUsername/s00/e01/test.txt
at/ac/uibk/pm/gXX/zidUsername/s00/e01/test2.txt


Returns:

If all configurations are set + files are contained, the script yields "Submission approved". In case of an error the corresponding error is printed. If files are not contained within the submission-folder, the missing files are listed. If there are files which are not required, they are listed too.


