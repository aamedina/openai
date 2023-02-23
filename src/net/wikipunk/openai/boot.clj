(ns net.wikipunk.openai.boot
  {:rdf/type :jsonld/Context})

(def openai
  {:rdf/type    :rdfa/PrefixMapping
   :rdfa/uri    "https://wikipunk.net/openai/"
   :rdfa/prefix "openai"})
