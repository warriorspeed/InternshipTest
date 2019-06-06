using InternshipTest.Person;
        using System.Collections.Generic;

        namespace InternshipTest.Institution
        {
public class University
{
    public string Name { get; private set; }

    private HashSet<Student> studentList = new HashSet<Student>();

    public University(string name)
    {
        Name = name;
    }

    public void AddStudent(Student student) => studentList.Add(student);

    public IReadOnlyCollection<Student> GetStudents() => studentList;
}
}