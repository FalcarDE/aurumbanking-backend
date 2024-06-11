//package de.fhe.cc.team4.aurumbanking.data
//
//import de.fhe.cc.team4.aurumbanking.model.TransactionDTO
//import de.fhe.cc.team4.aurumbanking.model.TransactionEntityModel
//import io.quarkus.hibernate.reactive.panache.PanacheRepository
//import io.smallrye.mutiny.Uni
//import jakarta.enterprise.context.ApplicationScoped
//
//
//@ApplicationScoped
//class TransactionRepository : PanacheRepository<TransactionEntityModel> {
//
//    //@Inject
//    //lateinit var entityManager: EntityManager
//    //lateinit var query : TypedQuery<TransactionEntityModel>
//    //fun findTransactionById(id: Long): List<TransactionEntityModel> {
//    //    query = entityManager.createQuery("SELECT t FROM transaction t WHERE t.id = :id", TransactionEntityModel::class.java)
//    //    query.setParameter("id", id);
//    //    return query.resultList
//    //}
//
//
//    fun findAllTransactionByDepotId(id: Long): Uni<List<TransactionEntityModel>> {
//        //find("select t.moneyvalue from transaction as t where t.depot_id = ?", id).project(TransactionEntityModel::class.java)
//        //    .list()
//        return find("#TransactionEntityModel.findAllTransactionByDepotId", id).list()
//    }
//
//
//    //fun findTransactionById(id: Long): Uni<List<TransactionEntityModel>> {
//    //   return find("select t from transaction t where t.id = ?", id).list()
//    //}
//
//    fun findTransactionById(id: Long): Uni<List<TransactionEntityModel>> {
//        return find("#TransactionEntityModel.findTransactionById", id).list()
//    }
//}
//
//
//