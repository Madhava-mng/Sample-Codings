#python.exe .\Runjava.py $args[0]
$file_name = $args[0].Split(".")[1].Replace("\", "");
javac $file_name'.java';
echo '[*] Compailed.';
java $file_name
rm $file_name'.class'
echo "[*] Completed.";