(ns net.wikipunk.chat
  "Chat with large language models."
  (:require
   [clojure.set :as set]
   [clojure.string :as str]
   [net.wikipunk.openai :as openai]))

(def alan-partridge
  {:prompt   "I want you to act like Alan Partridge who is a character on British radio and TV. I want you to respond and answer like the character of Alan Partridge would using the humor, tone, manner and vocabulary Alan Partridge would use. Do not write any explanations. You must know all of the knowledge of Alan Partridge."
   :messages []
   :username "alan"})

(def dumbledore
  {:prompt   "I want you to act like Albus Dumbledore who is the headmaster of Hogwarts in Harry Potter. I want you to respond and answer like the character of Dumbledore would using the humor, tone, manner and vocabulary Dumbledore would use. Do not write any explanations. You must know all of the knowledge of Albus Dumbledore."
   :messages []
   :username "albus"})

(defn chat
  "Chat with GPT-3.5 from the comfort of your Clojure REPL.

  End the chat by entering :q

  component -- a started instance of net.wikipunk.openai.Client

  :prompt -- Describe the personality of the virtual agent you
  want to interact with. Be very specific in your instructions. 
  Use `net.wikipunk.chat/alan-partridge` as an example and customize your own.
  Better prompts = better results.

  :messages -- A vector of ChatML message history between you and the agent
  you want to seed the conversation with."
  [component & {:keys [prompt messages username memory]
                :or   {memory 32}}]
  (loop [line    (read-line)
         history (into [{:openai.chat/role    "system"
                         :openai.chat/content prompt}]
                       messages)]
    (if (= line ":q")
      history
      (let [_        (println "user:" line)
            user     {:openai.chat/role    "user"
                      :openai.chat/content line}
            response (-> (openai/chat component
                                      :messages (mapv (fn [msg]
                                                        (set/rename-keys msg {:openai.chat/role    "role"    
                                                                              :openai.chat/content "content"
                                                                              :openai.chat/name    "name"}))
                                                      (conj history user)))
                         (get-in ["choices" 0 "message"])
                         (set/rename-keys {"role"    :openai.chat/role
                                           "content" :openai.chat/content
                                           "name"    :openai.chat/name}))]
        (println (str username ":") (:openai.chat/content response))
        (recur (read-line)
               (conj history user response))))))
