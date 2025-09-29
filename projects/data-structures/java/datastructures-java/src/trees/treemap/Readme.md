# Explanation:

### Class Definition:
- **`TreeMapExample:`** Main class to demonstrate usage of **`TreeMap`**.

### Creating a TreeMap:
- **`TreeMap<Integer, String> map = new TreeMap<>();:`** Creates a new **`TreeMap`** instance with **`Integer`** keys and **`String`** values.

### Adding Entries:
- **`map.put(3, "Three");:`** Adds key-value pairs to the **`TreeMap`**. The keys are automatically sorted in ascending order.

### Displaying the TreeMap:
- **`System.out.println("TreeMap contents: " + map);:`** Prints the contents of the **`TreeMap`**, showing keys in ascending order.

### Accessing Values:
- **`map.get(3);:`** Retrieves the value associated with the specified key.

### Checking Existence:
- **`map.containsKey(4);:`** Checks if a specified key exists in the map.
- **`map.containsValue("Five");:`** Checks if a specified value exists in the map.

### Removing Entries:
- **`map.remove(2);:`** Removes the entry with the specified key.

### Getting First and Last Entries:
- **`map.firstEntry();:`** Retrieves the entry with the smallest key.
- **`map.lastEntry();:`** Retrieves the entry with the largest key.

### Navigating the Map:
- **`map.ceilingEntry(3);:`** Retrieves the entry with the smallest key greater than or equal to the specified key.
- **`map.floorEntry(3);:`** Retrieves the entry with the largest key less than or equal to the specified key.

### Iterating Over Entries:
- **`map.entrySet();:`** Provides a set view of the mappings contained in the **`TreeMap`**.

# When to Use:
- **Sorted Order:** When you need to maintain a sorted order of keys automatically.
- **Range Queries:** When you need to perform range queries or navigate through entries based on the order of keys.
- **Efficient Lookup:** For efficient key-based lookups, insertions, and deletions with O(log n) time complexity.

# When Not to Use:
- **High Overhead:** When you don't need the keys to be sorted, or the overhead of maintaining the sorted order is not justified, as TreeMap can be slower than HashMap for simple key-value operations.
- **Memory Consumption:** For very large datasets, the overhead of maintaining a balanced tree structure can result in increased memory usage compared to other maps like HashMap.

# Key Points:
- **Navigable Map:** TreeMap implements NavigableMap which provides methods to get entries based on the closest match (e.g., ceilingEntry, floorEntry).
- **Sorted Keys:** Automatically sorts keys in their natural order or according to a provided comparator.
- **Thread-Safety:** TreeMap is not synchronized. For thread-safe operations, consider using Collections.synchronizedSortedMap to wrap the TreeMap.

This example illustrates how to use a TreeMap in Java to perform various operations while maintaining sorted order of keys.