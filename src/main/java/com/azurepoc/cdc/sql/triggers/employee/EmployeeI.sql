-- ==============================================
-- Create dml trigger template Azure SQL Database
-- ==============================================
-- Drop the dml trigger if it already exists
IF EXISTS(
  SELECT *
    FROM sys.triggers
   WHERE name = N'EmployeeI'
     AND parent_class_desc = N'OBJECT_OR_COLUMN'
)
	DROP TRIGGER dbo.EmployeeI
GO

CREATE TRIGGER dbo.EmployeeI
   ON  dbo.Employee
   AFTER INSERT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	DECLARE @EventData XML = EVENTDATA();

    -- Insert statements for trigger here
	INSERT INTO
   dbo.Employee_history( id, name, designation, department, updatedUserId, creationDate, updatedDate, operation )
   SELECT
      i.id,
      i.name,
      i.designation,
      i.department,
      i.updatedUserId,
	  i.creationDate,
      i.updatedDate,
      'I'
   FROM
      inserted i
END
GO
