#!/bin/bash
xelatex -shell-escape notebook
xelatex -shell-escape notebook
rm *.aux
rm *.toc
rm -r _minted-notebook
rm *.log
