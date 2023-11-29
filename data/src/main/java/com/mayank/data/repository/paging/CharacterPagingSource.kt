package com.mayank.data.repository.paging

//class CharacterPagingSource @Inject constructor(
//    private val characterService: CharacterService,
//    private val characterListEntityMapper: CharacterListEntityMapper,
//) : PagingSource<Int, CharacterModel>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
//        return try {
//            val currentPage = params.key ?: 1
//            val characters = characterService.getCharacters(
//                pageNumber = currentPage
//            )
//            LoadResult.Page(
//                data = characterListEntityMapper.mapFromListEntity(characters.characters),
//                prevKey = if (currentPage == 1) null else currentPage - 1,
//                nextKey = if (characters.characters.isEmpty()) null else 1
//            )
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//}