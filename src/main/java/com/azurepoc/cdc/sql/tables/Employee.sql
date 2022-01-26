/* To prevent any potential data loss issues, you should review this script in detail before running it outside the context of the database designer.*/
BEGIN TRANSACTION
SET QUOTED_IDENTIFIER ON
SET ARITHABORT ON
SET NUMERIC_ROUNDABORT OFF
SET CONCAT_NULL_YIELDS_NULL ON
SET ANSI_NULLS ON
SET ANSI_PADDING ON
SET ANSI_WARNINGS ON
COMMIT
BEGIN TRANSACTION
GO
CREATE TABLE dbo.Employee
	(
	id int NOT NULL IDENTITY (1, 1),
	name varchar(50) NULL,
	designation varchar(50) NULL,
	department varchar(50) NULL,
	updatedUserId varchar(MAX) NULL,
	creationDate datetime NOT NULL default GETDATE(),
	updatedDate datetime NOT NULL default GETDATE()
	)  ON [PRIMARY]
	 TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE dbo.Employee SET (LOCK_ESCALATION = TABLE)
GO
COMMIT
