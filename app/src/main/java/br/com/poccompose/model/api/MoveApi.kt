package br.com.poccompose.model.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoveApi @Inject constructor(
    private val movieService : MovieService
) {
    interface MovieService{

    }
}