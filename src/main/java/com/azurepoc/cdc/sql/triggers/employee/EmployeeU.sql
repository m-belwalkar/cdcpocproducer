-- ==============================================
-- Create dml trigger template Azure SQL Database
-- ==============================================
-- Drop the dml trigger if it already exists
IF EXISTS(
  SELECT *
    FROM sys.triggers
   WHERE name = N'EmployeeU'
     AND parent_class_desc = N'OBJECT_OR_COLUMN'
)
	DROP TRIGGER dbo.EmployeeU
GO

CREATE TRIGGER dbo.EmployeeU
   ON  dbo.Employee
   AFTER UPDATE
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	DECLARE @EventData XML = EVENTDATA();

    -- Insert statements for trigger here
	UPDATE  dbo.Employee
	SET updatedDate = getDate()
	FROM dbo.Employee e inner join inserted u
	on e.id=u.id

	INSERT INTO
   dbo.Employee_history( id, name, designation, department, updatedUserId, creationDate, updatedDate, operation)
   SELECT
      u.id,
      u.name,
      u.designation,
      u.department,
      u.updatedUserId,
	  u.creationDate,
      u.updatedDate,
      'U'
   FROM
      inserted u
END
GO
