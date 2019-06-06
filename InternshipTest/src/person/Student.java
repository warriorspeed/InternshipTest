using InternshipTest.Institution;
        using InternshipTest.Person;
        using System.IO;
        using static System.IO.FileMode;

        namespace InternshipTest.Repositories
        {
public static class UniversityRepository
{
    private static readonly string FileType = "university";

    public static void Save(this University university)
    {
        var fileName = GetFileName(university.Name);
        using (var fileStream = new FileStream(fileName, OpenOrCreate))
        using (var bin = new BinaryWriter(fileStream))
        {
            bin.Write(FileType);
            bin.Write(university.Name);
            bin.Write(university.GetStudents().Count);
            foreach (var student in university.GetStudents())
            {
                bin.Write(student.Name);
                bin.Write(student.Knowledge.Level);
            }
        }
    }

    public static University Load(string universityName)
    {
        var fileName = GetFileName(universityName);
        using (var fileStream = new FileStream(fileName, Open))
        using (var bin = new BinaryReader(fileStream))
        {
            var fileHead = new
            {
                Type = bin.ReadString(),
                        UniversityName = bin.ReadString(),
                        StudentCount = bin.ReadInt32()
            };
            var university = new University(fileHead.UniversityName);
            for (int i = 0; i < fileHead.StudentCount; i++)
            {
                var studentData = new
                {
                    Name = bin.ReadString(),
                            Level = bin.ReadInt32()
                };
                var student = new Student(studentData.Name)
                {
                    Knowledge = new Knowledge(studentData.Level)
                };
                university.AddStudent(student);
            }
            return university;
        }
    }
    public static bool IsExists(string universityName)
    {
        var fileName = GetFileName(universityName);
        return File.Exists(fileName);
    }

    public static string GetFileName(string universityName)
    {
        return $"{universityName}.{FileType}";
    }

}
}