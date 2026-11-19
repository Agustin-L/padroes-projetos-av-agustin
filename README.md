# Padrões de Projeto em Java

O repositório contém dois exercícios independentes. É necessário ter o JDK 21 (ou superior) instalado.

## Executar pelo terminal (PowerShell)

Na pasta raiz do projeto:

```powershell
$sources = Get-ChildItem questao1\src\*.java | ForEach-Object FullName
javac -encoding UTF-8 -d questao1\out $sources
Push-Location questao1\src
java -cp ..\out Main
Pop-Location

$sources = Get-ChildItem questao2\src\*.java | ForEach-Object FullName
javac -encoding UTF-8 -d questao2\out $sources
java -cp questao2\out Main
```

## Executar com um clique

- No Windows, dê dois cliques em `Rodar.bat` e escolha a questão. O arquivo compila antes de iniciar.
- No VS Code, abra **Executar e Depurar** (`Ctrl+Shift+D`), escolha **Rodar questão 1** ou **Rodar questão 2** e clique no botão verde. É necessário ter a extensão **Extension Pack for Java** instalada.

As pastas `questao1/diagrama` e `questao2/diagrama` contêm os diagramas PlantUML exigidos pela avaliação. Os arquivos `.class` são gerados nas pastas `out/` durante a execução e não precisam ser enviados.
