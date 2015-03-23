package main

import (
    "html/template"
    "log"
    "net/http"
)

type IndexPage struct {
}

func Index(w http.ResponseWriter, r *http.Request) {
    log.Println(r.RequestURI)
    t, _ := template.ParseFiles("templates/index.html")

    t.Execute(w, &IndexPage{})
}

func Search(w http.ResponseWriter, r *http.Request) {
    log.Println(r.RequestURI)

    query := r.FormValue("q")
    log.Println("Query: '" + query + "'")

    // TODO: send query to backend and display results

    http.Redirect(w, r, "/index", 302)
}