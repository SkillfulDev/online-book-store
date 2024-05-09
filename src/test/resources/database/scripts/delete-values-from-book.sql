DELETE FROM books_categories
WHERE book_id IN (
    SELECT id FROM books WHERE title IN (
                                         'Integrated actuating hardware',
                                         'Integrated well-modulated archive',
                                         'Managed client-driven toolset',
                                         'Innovative optimal groupware',
                                         'Universal even-keeled circuit',
                                         'Cross-group value-added hierarchy',
                                         'Business-focused logistical orchestration',
                                         'Decentralized web-enabled pricing structure',
                                         'User-friendly stable neural-net',
                                         'Operative bifurcated interface'
        )
);

DELETE FROM books
WHERE title IN (
                'Integrated actuating hardware',
                'Integrated well-modulated archive',
                'Managed client-driven toolset',
                'Innovative optimal groupware',
                'Universal even-keeled circuit',
                'Cross-group value-added hierarchy',
                'Business-focused logistical orchestration',
                'Decentralized web-enabled pricing structure',
                'User-friendly stable neural-net',
                'Operative bifurcated interface'
    );
