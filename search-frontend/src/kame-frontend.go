package main

import (
    "github.com/gorilla/mux"
    "html/template"
    "log"
    "net/http"
    "time"
)

type IndexPage struct {
}

func index(w http.ResponseWriter, r *http.Request) {
    log.Println(r.RequestURI)
    t, _ := template.ParseFiles("templates/index.html")

    t.Execute(w, &IndexPage{})
}

func search(w http.ResponseWriter, r *http.Request) {
    log.Println(r.RequestURI)

    query := r.FormValue("q")
    log.Println("Query: '" + query + "'")

    // TODO: send query to backend and display results

    http.Redirect(w, r, "/index", 302)
}

func main() {
    requestRouter := mux.NewRouter()
    requestRouter.HandleFunc("/", index).Methods("GET")
    requestRouter.HandleFunc("/index", index).Methods("GET")
    requestRouter.HandleFunc("/search", search).Methods("POST")
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
