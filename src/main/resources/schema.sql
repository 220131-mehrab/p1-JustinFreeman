CREATE TABLE "champion" (
    "Champion_ID" INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    "Champion_name" VARCHAR NOT NULL,
    "ClassType" VARCHAR NOT NULL,
    "Health" INT NOT NULL
);

CREATE INDEX "ifk_Champion_ID" ON "champion" ("Champion_ID");

INSERT INTO "champion" VALUES (1, 'Champion');
INSERT INTO "champion" VALUES (2,'ClassType');
INSERT INTO "champion" VALUES (3, 'Health');