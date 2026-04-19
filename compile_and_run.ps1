Write-Host "Compiling..."
$files = Get-ChildItem -Recurse -Filter *.java | Select-Object -ExpandProperty FullName
javac $files
if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation successful. Running..."
    java -cp . ro.watchmanager.main.Main
} else {
    Write-Host "Compilation failed."
}
