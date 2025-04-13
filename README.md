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

## Project Structure
HikeCollectionManager/ ├── Hike.java # Represents a single hike ├── CollectionManager.java # Manages the BST of Hikes ├── Client.java # Sample client interface ├── Testing.java # Unit tests for the application ├── hikes_sample1.txt # Sample hike data ├── hikes_sample2.txt ├── hikes_sample3.txt └── README.md

