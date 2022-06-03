package br.com.poccompose.real.enums

enum class ServerStatusEnum(val status: String) {
    INITIALIZE("N"),
    IN_PROCESSING("P"),
    FINISH("F")
}