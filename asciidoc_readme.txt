Follow http://blog.rainwebs.net/2010/02/25/how-to-create-handsome-pdf-documents-without-frustration/ 
in order to setup asciidoc with dblatex (for PDF generation support) using Cygwin under Windows.

To generate pdf from the README.adoc use the following command:

a2x -v -f pdf -L --asciidoc-opts='-a lang=en -v -b docbook -d book' --dblatex-opts='-P latex.output.revhistory=0' README.adoc
