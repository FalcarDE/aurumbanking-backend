# The Docs

The project uses [MkDocs](https://www.mkdocs.org) (with [Material Design](https://squidfunk.github.io/mkdocs-material/)) to generate a documentation site that contains the content of all READMEs. 
The site is created as part of the CI/CD pipeline and published to https://fhe-pmc-cc-doc.netlify.app. 
Have a look at `mkdocs.yml` and pipeline jobs `prepare_doc_pages`, `build_doc_pages` & `build_doc_pages` in `.gitlab-ci.yml` for details.

