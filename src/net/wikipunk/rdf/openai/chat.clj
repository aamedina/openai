(ns net.wikipunk.rdf.openai.chat
  "RDF vocabulary for ChatML"
  {:rdf/type :owl/Ontology}
  (:refer-clojure :exclude [name]))

(def Message
  "ChatML message"
  {:db/ident            :openai.chat/Message
   :rdf/type            [:owl/Class :jsonschema/ObjectSchema]
   :jsonschema/required ["role" "content"]})

(def role
  {:db/ident                :openai.chat/role
   :rdf/type                [:owl/ObjectProperty :jsonschema/DataSchema]
   :rdfs/domain             :openai.chat/Message
   :rdfs/range              :xsd/string
   :jsonschema/propertyName "role"
   :jsonschema/oneOf        ["system" "user" "assistant"]})

(def content
  {:db/ident                :openai.chat/content
   :rdf/type                [:owl/ObjectProperty :jsonschema/DataSchema]
   :rdfs/domain             :openai.chat/Message
   :rdfs/range              :xsd/string
   :jsonschema/propertyName "content"})

(def name
  {:db/ident                :openai.chat/name
   :rdf/type                [:owl/ObjectProperty :jsonschema/DataSchema]
   :rdfs/domain             :openai.chat/Message
   :rdfs/range              :xsd/string
   :jsonschema/propertyName "name"
   :jsonschema/oneOf        ["example_user" "example_application"]})
