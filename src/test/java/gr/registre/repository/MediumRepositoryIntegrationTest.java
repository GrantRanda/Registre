package gr.registre.repository;

public interface MediumRepositoryIntegrationTest {

    void testFindAllWhenMediaExist();

    void testFindMediumById();

    void testFindMediumByInvalidId();

    void testSaveMedium();

    void testDeleteMediumById();
}
