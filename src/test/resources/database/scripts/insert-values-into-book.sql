INSERT INTO books (id, title, author, isbn, price, description, cover_image)
VALUES
    (1, 'Next-Generation Neural Networks', 'Jane Doe', '978-1-234-5678-90-1', 29.95, 'A deep dive into modern neural network architectures and their applications.', 'https://example.com/cover1.jpg'),
    (2, 'Advanced Robotics and AI', 'John Smith', '978-1-234-5678-90-2', 35.99, 'Exploring the intersection of robotics and artificial intelligence.', 'https://example.com/cover2.jpg');

INSERT INTO books_categories (book_id, category_id)
SELECT 1, id FROM categories WHERE name = 'Fiction'
UNION ALL
SELECT 2, id FROM categories WHERE name = 'Fiction';
