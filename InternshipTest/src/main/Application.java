using System;
        using InternshipTest.Person;
        using InternshipTest.Institution;
        using InternshipTest.Institution.InterLink;
        using InternshipTest.Repositories;

        namespace InternshipTest
        {
class Program
{
    static void Main(string[] args)
    {
        var testUniversityName = "CH.U.I.";
        University university;

        if (UniversityRepository.IsExists(testUniversityName))
        {
            university = UniversityRepository.Load(testUniversityName);
        }
        else
        {
            var s = new Student("Alex");
            university = new University(testUniversityName);
            university.AddStudent(new Student("Andrew Kostenko") { Knowledge = new Knowledge(1)  });
            university.AddStudent(new Student("Julia Veselkina") { Knowledge = new Knowledge(5)  });
            university.AddStudent(new Student("Maria Perechrest"){ Knowledge = new Knowledge(10) });
            university.AddStudent(new Student("Dubov Slava")     { Knowledge = new Knowledge(15) });
            university.AddStudent(new Student("Sedelnikov Adam") { Knowledge = new Knowledge(20) });
            UniversityRepository.Save(university);
        }

        Internship internship = new Internship("Interlink") { University = university };
        Console.WriteLine("List of internship's students:");
        Console.WriteLine(internship.GetStudents());
        Console.ReadKey();
    }
}