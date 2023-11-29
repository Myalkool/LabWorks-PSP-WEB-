package by.iba.service;

import by.iba.exception.RepositoryException;
import by.iba.exception.ServiceException;
import by.iba.model.Person;
import by.iba.repository.RepositoryCreator;
import by.iba.repository.specification.PersonRepository;

import java.util.List;

public class PersonService {
    public List<Person> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            PersonRepository personRepository = repositoryCreator.getPersonRepository();
            return personRepository.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(Person person) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            PersonRepository personRepository = repositoryCreator.getPersonRepository();
            Person newp = new Person(person);
            personRepository.save(newp);
            System.out.println("adding");

        } catch (RepositoryException exception) {
           // throw new ServiceException(exception.getMessage(), exception);
        }
    }

}
