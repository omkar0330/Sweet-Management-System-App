public interface SweetRepository extends JpaRepository<Sweet, Long> {
    List<Sweet> findByNameContainingIgnoreCase(String name);
    List<Sweet> findByCategory(String category);


    @Query("SELECT s FROM Sweet s WHERE s.price BETWEEN :min AND :max")
    List<Sweet> findByPriceRange(@Param("min") double min, @Param("max") double max);
}
