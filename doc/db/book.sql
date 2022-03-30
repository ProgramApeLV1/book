/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : book_bak

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 30/03/2022 14:23:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cs_book_info
-- ----------------------------
DROP TABLE IF EXISTS `cs_book_info`;
CREATE TABLE `cs_book_info`  (
  `id` int(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `bookCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书号',
  `bookName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `publishTime` datetime(0) NULL DEFAULT NULL COMMENT '出版时间',
  `unitPrice` double(8, 2) NULL DEFAULT NULL COMMENT '单价',
  `publishingHouse` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社名称',
  `typeCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书本类型',
  `status` int(5) NULL DEFAULT NULL COMMENT '0：失效 1： 启用 2：禁用',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书本基础信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cs_book_info
-- ----------------------------
INSERT INTO `cs_book_info` VALUES (3, '3333', '史记', '儒勒·凡尔纳', '1871-11-16 00:00:00', 200.00, '暂无', 'STS', 1, '2019-06-02 04:44:01');
INSERT INTO `cs_book_info` VALUES (5, '4444', '海底两万里', '儒勒·凡尔纳', '1870-06-13 00:00:00', 66.00, '暂无', 'STS', 1, '2019-06-05 13:23:49');

-- ----------------------------
-- Table structure for cs_book_type
-- ----------------------------
DROP TABLE IF EXISTS `cs_book_type`;
CREATE TABLE `cs_book_type`  (
  `id` int(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `typeName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `status` int(5) NULL DEFAULT NULL COMMENT '0：失效 1： 启用 2：禁用',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书本基础信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cs_book_type
-- ----------------------------
INSERT INTO `cs_book_type` VALUES (1, 'STS', '实体书', 1, '2022-03-30 16:18:48');
INSERT INTO `cs_book_type` VALUES (2, 'DZS', '电子书', 1, '2022-03-30 16:18:48');

-- ----------------------------
-- Table structure for cs_in_stock_record
-- ----------------------------
DROP TABLE IF EXISTS `cs_in_stock_record`;
CREATE TABLE `cs_in_stock_record`  (
  `id` int(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `inCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入库单号',
  `refCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借出单号',
  `bookCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书号',
  `typeCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `inNum` int(8) NULL DEFAULT NULL COMMENT '归还数量',
  `status` int(5) NULL DEFAULT NULL COMMENT 'DRK:待入库,YRK:已入库',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `returnTime` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  `createName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '归还记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cs_in_stock_record
-- ----------------------------

-- ----------------------------
-- Table structure for cs_library_stock
-- ----------------------------
DROP TABLE IF EXISTS `cs_library_stock`;
CREATE TABLE `cs_library_stock`  (
  `id` int(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `bookCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书号',
  `typeCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `sumStock` int(8) NULL DEFAULT NULL COMMENT '库存数量',
  `canStock` int(8) NULL DEFAULT NULL COMMENT '可借数量',
  `outStock` int(8) NULL DEFAULT NULL COMMENT '借出数量',
  `status` int(5) NULL DEFAULT NULL COMMENT '0：失效 1： 启用 2：禁用',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书本基础信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cs_library_stock
-- ----------------------------

-- ----------------------------
-- Table structure for cs_out_stock_record
-- ----------------------------
DROP TABLE IF EXISTS `cs_out_stock_record`;
CREATE TABLE `cs_out_stock_record`  (
  `id` int(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `outCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出库单号',
  `refCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归还单号',
  `bookCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书号',
  `typeCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `outNum` int(8) NULL DEFAULT NULL COMMENT '借出数量',
  `status` int(5) NULL DEFAULT NULL COMMENT 'DCK:待出库,YCK:已出库',
  `day` int(11) NULL DEFAULT NULL COMMENT '借用天数',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `createName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借出记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cs_out_stock_record
-- ----------------------------

-- ----------------------------
-- Table structure for os_menu
-- ----------------------------
DROP TABLE IF EXISTS `os_menu`;
CREATE TABLE `os_menu`  (
  `id` int(19) NOT NULL AUTO_INCREMENT COMMENT '平台系统菜单  该数据也来源于子系统',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `code` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单编码',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单介绍',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `pCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单id',
  `seq` tinyint(2) NOT NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '菜单状态',
  `resourceType` tinyint(2) NOT NULL DEFAULT 0 COMMENT '菜单类别  0：一级菜单   1：二级菜单   ',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Index_code`(`code`) USING BTREE,
  INDEX `Index_pCode`(`pCode`) USING BTREE,
  INDEX `Index_seq`(`seq`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of os_menu
-- ----------------------------
INSERT INTO `os_menu` VALUES (1, '系统管理', 'ME1532481370', '', '系统管理', 'icon-system', 'NONE', 1, 1, 0, '2017-09-09 23:22:26');
INSERT INTO `os_menu` VALUES (2, '基础管理', 'ME1532481371', NULL, '基础管理', 'icon-performance', 'NONE', 2, 1, 0, '2018-06-01 09:26:09');
INSERT INTO `os_menu` VALUES (3, '菜单管理', 'ME1532481376', '/menuCont/gotoMenuInfoPage', '菜单管理', 'icon-taginfo', 'ME1532481370', 1, 1, 1, '2017-09-09 23:22:26');
INSERT INTO `os_menu` VALUES (4, '用户管理', 'ME1532481377', '/menuCont/gotoUserInfoPage', '用户管理', 'icon-taginfo', 'ME1532481370', 1, 1, 1, '2017-09-09 23:22:26');
INSERT INTO `os_menu` VALUES (6, '书本信息', 'ME1546768996094', '/menuCont/gotoBookInfoPage', '书本信息', 'icon-taginfo', 'ME1532481371', 1, 1, 1, '2019-01-06 10:03:16');
INSERT INTO `os_menu` VALUES (7, '借阅管理', 'ME1546786454934', 'NONE', '', 'icon-taginfo', 'NONE', 3, 1, 0, '2019-01-06 14:54:15');
INSERT INTO `os_menu` VALUES (8, '借出管理', 'ME1546786470336', '/menuCont/gotoBoorowBookPage', '', 'icon-taginfo', 'ME1546786454934', 1, 1, 1, '2019-01-06 14:54:30');
INSERT INTO `os_menu` VALUES (9, '归还记录', 'ME1546789289772', '/menuCont/gotoReturnBookPage', '', 'icon-taginfo', 'ME1546786454934', 2, 1, 1, '2019-01-06 15:41:30');
INSERT INTO `os_menu` VALUES (10, '书本类别', 'ME1559115267100', '/menuCont/gotoBookTypePage', '', 'icon-taginfo', 'ME1532481371', 10, 1, 1, '2019-05-29 07:34:27');

-- ----------------------------
-- Table structure for os_role
-- ----------------------------
DROP TABLE IF EXISTS `os_role`;
CREATE TABLE `os_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `pid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of os_role
-- ----------------------------
INSERT INTO `os_role` VALUES (1, '系统管理员', NULL, 1, '2022-03-30 11:35:37');

-- ----------------------------
-- Table structure for os_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `os_role_menu`;
CREATE TABLE `os_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of os_role_menu
-- ----------------------------
INSERT INTO `os_role_menu` VALUES (1, '1', '1');
INSERT INTO `os_role_menu` VALUES (2, '1', '3');
INSERT INTO `os_role_menu` VALUES (3, '1', '4');

-- ----------------------------
-- Table structure for os_user
-- ----------------------------
DROP TABLE IF EXISTS `os_user`;
CREATE TABLE `os_user`  (
  `id` int(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编码{系统级别：自动生成并且唯一,运用于云平台中}',
  `loginName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `userName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `workDutyCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作职责编码',
  `identity` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `deptCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `jobNumber` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职员工号',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别 0:男 1:女',
  `age` int(2) NULL DEFAULT 0 COMMENT '年龄',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `userType` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'TET' COMMENT '用户类型 ',
  `status` int(5) NULL DEFAULT NULL COMMENT '0：失效 1： 启用 2：禁用',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户登入' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of os_user
-- ----------------------------
INSERT INTO `os_user` VALUES (1, 'USER1546791528611', 'root', '最高权限管理员', '123456', 'c12312312', '35050505050505050', 'dept001', '2336555', '0', 20, '18050505050', 'root', 1, '2019-01-03 13:50:53');
INSERT INTO `os_user` VALUES (2, 'USER1546791528655', 'book_admin', '图书管理员', '123456', 'c12312311', '35050505050505051', 'dept002', '111111', '1', 22, '18050276012', 'admin', 1, '2019-01-06 16:18:49');

-- ----------------------------
-- Table structure for os_user_role
-- ----------------------------
DROP TABLE IF EXISTS `os_user_role`;
CREATE TABLE `os_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of os_user_role
-- ----------------------------
INSERT INTO `os_user_role` VALUES (1, '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
