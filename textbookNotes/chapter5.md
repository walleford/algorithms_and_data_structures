# Chapter 5 Strings

## 5.1 String Sorting

## 5.2 Tries

  - to better implement searching with strings, we will utilize strings as a symbol table
  - goal of tries: 
    - search hits take time proportional to the length of the search key
    - search misses involve examining only a few characters
  - we will extend the previously used symbol table API to include character-based operations defined for String keys
  - Differences with new String ST API and previous ST API:
    - generic Key type is replaced with the concrete String
    - three new methods: longestPrefixOf(), keysWithPrefix(), keysThatMatch()
  - `longestPrefixOf()` takes a string as argument and returns the longest key in the symbol table that is a prefix
    of that string. 
  - `keysWithPrefix()` takes a string as argument and returns all keys in the symbol table having that string as a 
    prefix.
  - `keysThatMatch()` takes a string as an argument and returns all the keys in the symbol table that match that string,
    in the sense that a period (.) in the argument string matches any character
  - Trie:
    - a trie is a data structure built from the characters of the string keys that allows us to use the characters of
      the search key to guide the search.
  - Trie basic properties:
    - tries are data structures composed of _nodes_ that contain _links_ that are either null or references to other 
      nodes.
    - Each node is pointed to by just one node, its parent, and each node has _R_ links, where _R_ is the alphabet size
    - When we draw a trie, we omit null links
    - Each link corresponds to a character value, and we label each node by the link coming into it.
    - Each node has a value, null or the value associated with one of the string keys in the symbol table.
      - Specifically, we store the value of each key in the node corresponding to the key's last character
    - _Nodes with null values exist to facilitate search in the trie and do not correspond to keys_
  - Searching in a trie:
    - each node in the trie has a link corresponding to each possible string character
    - we start at the root, then follow the link associated with the first character in the key, from that node we
      follow the link associated with the second character in the key; from that node we follow the link associated
      with the third character in the key, and so forth until reaching the last character or a null link.
    - At this point on of these 3 conditions will be true:
      - value at the node corresponding to the last character in the key is not null. **SEARCH HIT**
      - Value in the node corresponding to the last character in the key is null. **SEARCH MISS**
      - Search terminated with a null link. **SEARCH MISS**
  - Insertion in a trie:
    - we insert first by searching: in a trie that means using the characters of the key to guide us down the trie
      until reaching the last character of the key or a null link. At this point one of two is true:
      - we encountered a null link before reaching the last character of the key, which means we need to add the
        remaining characters to the trie
      - we encountered the last character of the key before reaching a null link, in this case we set the nodes value to
        the value associated with the new key
  - Node representation
    - each node has **R** links, one link for each character in the specified alphabet
    - characters and keys are implicitly stored in the data structure
  - Collecting Keys
    - we accumulate the string keys in a `Queue` and collect them all together with a private recursive method `collect()`
    - Each time we visit a node via a call with `collect()` with that node as the first argument, the second argument is
      the string associated with that node. 
    - T