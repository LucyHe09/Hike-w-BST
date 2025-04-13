# Hike Collection Manager

A Java application for organizing, filtering, and persisting a collection of hiking trails using a custom binary search tree (BST) implementation.

Each `Hike` object includes:
- **Name** – the name of the trail
- **Length** – the total distance in miles
- **Review** – a user rating on a 1–5 scale

Hikes are stored in a BST sorted first by length (ascending), then by review score (descending), and finally by name (alphabetically).

---

## Features

- Custom recursive binary search tree (BST)
- Efficient insertion, traversal, and search operations
- File input/output support (custom pre-order format)
- `filter()` method to find hikes based on user criteria
- Clear string formatting for display and persistence
- Comprehensive test suite

---
## Class Breakdown

### `Hike.java`
Defines the `Hike` class and its comparison logic. Implements:
- `Comparable<Hike>` — for sorting in the BST
- `toString()` — user-friendly and save/load compatible format
- `equals()` and `hashCode()` — based on name, length, and review
- `static Hike.parse(Scanner input)` — constructs a hike from file input

### `CollectionManager.java`
Manages a binary search tree of `Hike` objects. Includes:
- Recursive insert and traversal methods
- `toString()` — returns all hikes in sorted order
- `filter(double maxLength, int minReview)` — returns a list of hikes meeting criteria
- `save(PrintWriter)` and `load(Scanner)` — handles custom file format

### `Client.java`
Demonstrates the functionality of the collection manager. Can:
- Load hikes from a file
- Display the collection
- Add new hikes
- Save the collection to a file

### `Testing.java`
Unit tests for all major methods, including:
- BST ordering
- File I/O behavior
- Filter logic
- Duplicate and edge case handling

---

## Sample File Format

Hikes are saved in a pre-order traversal format, readable by the `parse()` method.

**Example:**

---

## Filtering

Use `filter(maxLength, minReview)` to get a list of hikes that meet your criteria:

```java
List<Hike> scenicShortHikes = manager.filter(4.0, 4);
```
---
## Project Structure

```plaintext
HikeCollectionManager/
├── Hike.java              # Represents a single hike
├── CollectionManager.java # Manages the BST of Hikes
├── Client.java            # Sample client interface
├── Testing.java           # Unit tests for the application
├── InOrder.txt      # Sample hike data
├── NatPark.txt
└── README.md
```
---


