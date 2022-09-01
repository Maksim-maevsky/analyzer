INSERT INTO part_storages (id, name)
VALUES (1, 'TANGDE') ON CONFLICT (id) DO NOTHING;
INSERT INTO part_storages (id, name)
VALUES (2, 'PLASTIC') ON CONFLICT (id) DO NOTHING;
INSERT INTO part_storages (id, name)
VALUES (3, 'MIKHNEVO') ON CONFLICT (id) DO NOTHING;