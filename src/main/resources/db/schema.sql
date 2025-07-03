/*
 Navicat Premium Dump SQL

 Source Server         : (RockyLinux)172.16.130.127(v17.2)
 Source Server Type    : PostgreSQL
 Source Server Version : 170002 (170002)
 Source Host           : 172.16.130.127:5432
 Source Catalog        : mine
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 170002 (170002)
 File Encoding         : 65001

 Date: 03/07/2025 19:29:21
*/


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
    "id" serial NOT NULL,
    "username" varchar COLLATE "pg_catalog"."default" NOT NULL,
    "email" varchar(255) COLLATE "pg_catalog"."default",
    "password" varchar COLLATE "pg_catalog"."default" NOT NULL,
    "age" int4 NOT NULL,
    "isadmin" int2 NOT NULL DEFAULT 0,
    "address" varchar COLLATE "pg_catalog"."default",
    "introduction" text COLLATE "pg_catalog"."default",
    "sex" int2 NOT NULL DEFAULT 0,
    "phone" varchar COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."user"."id" IS '自增id';
COMMENT ON COLUMN "public"."user"."username" IS '用户名';
COMMENT ON COLUMN "public"."user"."email" IS '邮箱';
COMMENT ON COLUMN "public"."user"."password" IS '密码';
COMMENT ON COLUMN "public"."user"."age" IS '年龄';
COMMENT ON COLUMN "public"."user"."isadmin" IS '是否超级管理员';
COMMENT ON COLUMN "public"."user"."address" IS '地址';
COMMENT ON COLUMN "public"."user"."introduction" IS '个人简介';
COMMENT ON COLUMN "public"."user"."sex" IS '0=男，1=女';
COMMENT ON COLUMN "public"."user"."phone" IS '手机号';

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("id");