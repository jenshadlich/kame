package main

import (
    "github.com/gorilla/mux"
    "log"
    "net/http"
    "time"
)

func main() {
    requestRouter := mux.NewRouter()
    requestRouter.HandleFunc("/", Index).Methods("GET")
    requestRouter.HandleFunc("/index", Index).Methods("GET")
    requestRouter.HandleFunc("/search", Search).Methods("POST")
    // static content
    requestRouter.PathPrefix("/img/").Handler(http.StripPrefix("/img/", http.FileServer(http.Dir("./static/img/"))))
    requestRouter.PathPrefix("/css/").Handler(http.StripPrefix("/css/", http.FileServer(http.Dir("./static/css/"))))
    requestRouter.PathPrefix("/js/").Handler(http.StripPrefix("/js/", http.FileServer(http.Dir("./static/js/"))))

    server := &http.Server{
        Addr:           ":8000",
        Handler:        requestRouter,
        ReadTimeout:    10 * time.Second,
        WriteTimeout:   10 * time.Second,
        MaxHeaderBytes: 1 << 13, // 8K
    }

    log.Println("Listening...")
    log.Fatal(server.ListenAndServe())
}
