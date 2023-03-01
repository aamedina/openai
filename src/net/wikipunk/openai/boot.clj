(ns net.wikipunk.openai.boot
  {:rdf/type :jsonld/Context}
  (:require
   [net.wikipunk.rdf.jsonschema]))

(def openai
  {:rdf/type    :rdfa/PrefixMapping
   :rdfa/uri    "https://wikipunk.net/openai/"
   :rdfa/prefix "openai"})

(def openai.chat
  {:rdf/type    :rdfa/PrefixMapping
   :rdfa/uri    "https://wikipunk.net/openai/chat/"
   :rdfa/prefix "openai.chat"})
