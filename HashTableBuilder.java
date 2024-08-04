class HashTableBuilder {
    Element [] hashTable;
    int tableSize;
    
    public HashTableBuilder(int numKeyWords) {
        tableSize = nextPrime(numKeyWords * 2);
        hashTable = new Element[tableSize];
    }
    
    void insert(String keyword, Article articleRecord) {
        
        int index = getNextQuadProbeIndex(keyword);
        //System.out.println("Test Insert" + index + " " + keyword);

        if(hashTable[index] == null){
            hashTable[index] = new Element(keyword);
        }
        
        hashTable[index].list.addFirst(articleRecord);
        
    }
    
    private int getNextQuadProbeIndex(String keyword) {
        int probe = 1;
        int key = convertStringToInt(keyword);
        int index = key % tableSize;
        while (hashTable[index] != null && !((String)hashTable[index].keyword).equals(keyword)) {
            /*if(convertStringToInt((String)hashTable[index].keyword) == key){
                //hashTable[index].list.add(index, hashTable[index].Element);
                break;
            }
                */
            System.out.printf("%s conflicts with %s at index: %d\n", keyword, hashTable[index].keyword, index);
            index = ((key % tableSize) + (int) Math.pow(probe++, 2)) % tableSize;
        }
        
        return index; 
    }
    
    private int convertStringToInt(String keyword) {
        int sum = 0;
        for (int i=0; i < keyword.length(); i++)
            sum += (int) keyword.charAt(i);
        return sum;
    }
    
    private int nextPrime(int num) {
        num++;
        for (int i=2; i < num; i++) {
            if (num % i == 0) {
                num++;
                i = 2;
            }
            else 
                continue;
        }
        
        return num;
    }
    /* 
    
    public void print(int index){
        Node cur = hashTable[index].list.getHead();
        while(cur != null){
            System.out.println(cur.toString());
            cur = cur.next;
        }
        //System.out.println();
        
    }
        */

    public int searchKey(String look){
        for(int i = 0; i < tableSize; i++){
            if(hashTable[i] != null){
                if(((String)hashTable[i].keyword).equals(look)){
                    return i;
                }
            }
        }
        return -1;

    }
    

    
    //Search
    
    
    
    
}