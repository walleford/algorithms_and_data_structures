/**
 * C11-PA Elementary Symbol Table
 * 03/08/2024
 */
package b03_searches.symboltable;

import java.util.Iterator;

/**
 * Simple implementation of a symbol table using an unsorted linked list
 */
public class UnsortedLinkedListSymbolTable<Key, Value> implements SymbolTable<Key, Value>
{

    /**
     * Stores the association between a key and value as a node in a linked list.
     */
    private class Association
    {
        /**
         * Key associated with a specific value.
         * Used for searching, putting, getting, deleting.
         */
        private Key key;
        /**
         * Variable containing the value for the specific key
         */
        private Value value;

        /**
         * The variable containing the link to the next association in the linked list
         */
        private Association nextAssociation;

        /**
         * Creates the actual association between the key and value, places it at the beginning of the linked list.
         * @param key : key for which the value is associated
         * @param value : value assigned to a key
         * @param nextAssociation : the link to the next association in the list
         */
        public Association(Key key, Value value, Association nextAssociation)
        {
            this.key = key;
            this.value = value;
            this.nextAssociation = nextAssociation;
        }
    }

    private int numberOfAssociations; // the total number of associations in the symbol table

    private Association firstAssociation; // the first association in the symbol table

    /**
     * This is used to initialize and create the first empty symbol table
     */
    public UnsortedLinkedListSymbolTable()
    {
        this.numberOfAssociations = 0;
        this.firstAssociation = null;
    }

    /**
     * Puts a new key/value pair into the linked list as an association, if the value isn't null.
     * If the value is null, it calls the {@link #delete(Key)} function on the specified key
     */
    @Override
    public void put(Key key, Value value)
    {
        if (key == null) // if key is null, something went wrong...
        {
            throw new NullPointerException();
        }
        if (value != null) // as long as the value isn't null, we will add the new association
        {
            Association a = getAssociation(key);
            if (a!=null) // if the key exists, update the value to this new value
            {
                a.value = value;
            }
            else // if the key doesn't exist, create a new association and make it the firstAssociation
            {
                a = new Association(key, value, firstAssociation);
                firstAssociation = a;
                numberOfAssociations++;
            }
        }
        else // if value is null, call delete()
        {
            delete(key);
        }
    }

    @Override
    public Value get(Key key)
    {
        Association a = getAssociation(key);
        if (a==null)
            return null;
        return a.value;
    }

    /**
     * Pulls the defined association for the node, if any, null if none.
     * It loops through the linked list until it finds the association with the equivalent key
     * @param key : key to be searched for
     * @return : association if key exists, null if not.
     */
    private Association getAssociation(Key key)
    {
        Association node = firstAssociation;
        while (node != null)
        {
            if (node.key.equals(key))
                return node;
            node = node.nextAssociation;
        }
        return null;
    }

    @Override
    public void delete(Key key)
    {
        if (firstAssociation == null)
            return;
        if (firstAssociation.key.equals(key))
        { // if the one to be deleted if the first association, make the nextAssociation the first and delete the first
            firstAssociation = firstAssociation.nextAssociation;
            // do we null out the firstAssociation here?
            numberOfAssociations--;
            return;
        }

        /**
         * assoc is the one being assessed, if it has the same key as the key that needs to be deleted, set the
         * previousAssoc to the next Association, so we can move forward.
         */
        Association prevAssoc = firstAssociation;
        Association assoc = firstAssociation.nextAssociation;
        while (assoc!=null)
        {
            if (assoc.key.equals(key))
            {
                prevAssoc.nextAssociation = assoc.nextAssociation;
                // do we need to null out the assoc here?
                numberOfAssociations--;
                return;
            }
            prevAssoc = assoc;
            assoc = assoc.nextAssociation;
        }
    }

    @Override
    public boolean contains(Key key)
    {
        return getAssociation(key) != null;
    }

    @Override
    public boolean isEmpty()
    {
        return numberOfAssociations == 0; // could also be firstAssociation == null;
    }

    @Override
    public int size()
    {
        return numberOfAssociations;
    }

    @Override
    public Iterable<Key> keys()
    {
        return new Iterable<Key>()
        {
            @Override
            public Iterator<Key> iterator()
            {
                return new Iterator<Key>()
                {
                    Association next = firstAssociation;
                    @Override
                    public boolean hasNext()
                    {
                        return next != null;
                    }

                    @Override
                    public Key next()
                    {
                        Association result = next;
                        next = next.nextAssociation;
                        return result.key;
                    }
                };
            }
        };
    }

    @Override
    public String toString()
    {
        String result = "{";
        Association next = firstAssociation;
        String separator = "";
        while (next != null)
        {
            result += separator + next.key + "(" + next.value + ")";
            separator = ", ";
            next = next.nextAssociation;
        }
        result += "}";
        return result;
    }
}
