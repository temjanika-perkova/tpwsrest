package rest

import grails.converters.JSON
import grails.converters.XML
import org.springframework.dao.DataIntegrityViolationException

class LibraryController {
    def index() {
        switch (request.getMethod()){
            case "GET":
                if(params.get('id')){
                    def libraryInstance = Library.get(params.id)
                    if (libraryInstance) {
                        withFormat {
                            json {render libraryInstance as JSON}
                            xml {render libraryInstance as XML}
                        }
                    }
                    else {
                        render(status: 404, text: "Library not found")
                    }
                }
                else {
                    def libraries = Library.list()
                    if(libraries){
                        withFormat {
                            json {render libraries as JSON}
                            xml {render libraries as XML}
                        }
                    }
                }
                break
            case "POST":
                println(request.getMethod())
                def libraryInstance = new Library(params)
                if(libraryInstance.save(flash:true))
                    render (status: 201, text: "The library is created")
                else
                    render (status: 400, text:"The library can't be created")
                break
            case "PUT":
                def libraryInstance = Library.get(params.id)
                if(libraryInstance){
                    libraryInstance.properties=params
                    if(libraryInstance.save(flush:true))
                        render(status: 200, text: "Library update successfully")
                }
                else
                    render(status: 404, text: "Library not Found")
                break
            case "DELETE":
                def libraryInstance = Library.get(params.id)
                if(libraryInstance){
                    try {
                        libraryInstance.delete(flush: true)
                        render(status: 200, text: "Library deleted successfully")
                    } catch (DataIntegrityViolationException e){
                        render(status: 500, text: "Library can not be deleted...")
                        e.printStackTrace()
                    }
                }
                break
        }
    }


    def gestionBook(){
        def libraryInstance = Library.get(params.libid)
        if(libraryInstance){
            switch (request.getMethod()){
                case "GET":
                    def book =Book.get(params.id)
                    if(libraryInstance.books.contains(book)){
                        response.status=301
                        redirect action:'index', controller: 'book', params:params, permanent:true
                    }
                    else if(params.get('id')==null){
                        redirect action:'getListBooks',controller: 'book', params:params, permanent:true
                    }
                    else {
                        render(status: 404, text: "Library does not contain the book")
                    }
                    break
                case "POST":

                    if(Book.list().contains(Book.get(params.id))){
                        render(status: 405, text: "Book already exists")
                    }
                    else {
                        response.status=301
                        forward action: 'index', controller: 'book', params: [library:params.libid]
                    }
                    break
                case "PUT":
                    if(libraryInstance.books.contains(Book.get(params.id))){
                        response.status=301
                        redirect action:'index',controller:'book', params:params, permanent:true
                    }
                    else{
                        render(status: 404,text: "Library does not contain the book")
                    }
                    break
                case "DELETE":
                    def book=Book.get(params.id)
                    if(book){
                        if(libraryInstance.books.contains(book)){
                            response.status=301
                            redirect action:'index',controller:'book', params:params, permanent:true
                        }
                        else {
                            render(status: 404, text: "Library doesnt contain the book")
                        }
                    }
                    else {
                        render(status: 404, text: "Book does not exist")
                    }
                    break
            }
        }
        else{
            render(status: 404, text: "Library not found")
        }

    }
}
