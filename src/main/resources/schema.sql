CREATE TABLE "champion" (
    "Champion_ID" INT PRIMARY KEY NOT NULL,
    "Champion_name" VARCHAR NOT NULL,
    "ClassType" VARCHAR NOT NULL,
    "Health" INT NOT NULL
);

CREATE INDEX "ifk_Champion_ID" ON "champion" ("Champion_ID");

INSERT INTO "champion" VALUES (1,'Aatrox', 'Warrior', 580);
INSERT INTO "champion" VALUES (2, 'Ahri', 'Mage', 418);
INSERT INTO "champion" VALUES (3,'Akali', 'Assasin', 500);
INSERT INTO "champion" VALUES (4, 'Akshan', 'Ranged-Assassin', 500);