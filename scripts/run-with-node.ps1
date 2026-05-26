$ErrorActionPreference = 'Stop'

$fnmPath = "$env:LOCALAPPDATA\Microsoft\WinGet\Packages\Schniz.fnm_Microsoft.Winget.Source_8wekyb3d8bbwe\fnm.exe"
if (-not (Test-Path $fnmPath)) {
  $fnmCommand = Get-Command fnm -ErrorAction SilentlyContinue
  if ($fnmCommand) {
    $fnmPath = $fnmCommand.Source
  }
}

if (-not (Test-Path $fnmPath)) {
  Write-Error "No se encontró fnm. Instálalo sin admin con: winget install Schniz.fnm --scope user"
}

$projectRoot = Split-Path $PSScriptRoot -Parent
$nodeVersionFile = Join-Path $projectRoot '.node-version'
$nodeVersion = '22.12.0'

if (Test-Path $nodeVersionFile) {
  $nodeVersion = (Get-Content $nodeVersionFile -Raw).Trim()
}

& $fnmPath install $nodeVersion | Out-Null

$nodeExe = Join-Path $env:APPDATA "fnm\node-versions\v$nodeVersion\installation\node.exe"
if (-not (Test-Path $nodeExe)) {
  Write-Error "No se encontró Node $nodeVersion instalado por fnm."
}

if ($args.Count -gt 0 -and $args[0] -eq 'ng') {
  $ngScript = Join-Path $projectRoot 'node_modules\@angular\cli\bin\ng.js'
  if (-not (Test-Path $ngScript)) {
    Write-Error "No se encontró Angular CLI. Ejecuta: npm install"
  }

  $ngArgs = $args[1..($args.Count - 1)]
  & $nodeExe $ngScript @ngArgs
  exit $LASTEXITCODE
}

& $nodeExe @args
exit $LASTEXITCODE
