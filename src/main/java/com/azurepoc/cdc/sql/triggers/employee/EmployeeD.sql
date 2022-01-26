-- ==============================================
-- Create dml trigger template Azure SQL Database
-- ==============================================
-- Drop the dml trigger if it already exists
IF EXISTS(
  SELECT *
    FROM sys.triggers
   WHERE name = N'EmployeeD'
     AND parent_class_desc = N'OBJECT_OR_COLUMN'
)
	DROP TRIGGER dbo.EmployeeD
GO

CREATE TRIGGER dbo.EmployeeD
   ON  dbo.Employee
   AFTER DELETE
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	DECLARE @EventData XML = EVENTDATA();

    -- Insert statements for trigger here
	INSERT INTO
   dbo.Employee_history( id, name, designation, department, updatedUserId, creationDate, updatedDate, operation)
   SELECT
      d.id,
      d.name,
      d.designation,
      d.department,
      CASE WHEN SUSER_SNAME() IS NULL THEN d.updatedUserId ELSE SUSER_SNAME() END,
	  d.creationDate,
      d.updatedDate,
      'D'
   FROM
      deleted d
END
GO
