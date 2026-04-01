param(
    [string]$InputFile = "benchmarks/cyclic/c_40_0.txt"
)

$ErrorActionPreference = "Stop"

# Always run relative to the project root where this script lives.
Set-Location -Path $PSScriptRoot

if (-not (Test-Path -Path "src" -PathType Container)) {
    throw "Could not find 'src' folder. Run this script from the project root."
}

if (-not (Test-Path -Path $InputFile -PathType Leaf)) {
    throw "Input file not found: $InputFile"
}

Push-Location "src"
try {
    javac *.java
}
finally {
    Pop-Location
}

java -cp src Main $InputFile

