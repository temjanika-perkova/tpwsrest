package rest

import grails.converters.JSON
import grails.converters.XML

class BookController {

    def index() {
        switch (request.getMethod()){
            case "GET":
                if(params.get('id')){
                    def bookInstance = Book.get(params.id)
                    if (bookInstance) {
                        withFormat {
                            json {render bookInstance as JSON}
                            xml {render bookInstance as XML}
                        }
                    }
                    else {
                        render(status: 404, text: "Book not found")
                    }
                }
                else {
                    def books = Book.list(order:"asc")
                    if(books){
                        withFormat {
                            json {render books as JSON}
                            xml {render books as XML}
                        }
                    }
                }
                break
            case "POST":
                def bookInstance = new Book(params)
                if(bookInstance.save(flush:true))
                    render(status: 200,text: "Book created successfully")
                else render(status: 400, text: "The book can not be created")
                break
            case "PUT":
                def book = Book.get(params.id)
                if(book){
                    book.properties=params
                    if(book.save(flush:true))
                        render(status: 200, text: "Book updated successfully")
                    else
                        render(status: 400, text: "The book can not be updated")
                }
                else
                    render(status: 404, text: "Book not found...")
                break
            case "DELETE":
                def bookInstance = Book.get(params.id)
                if(bookInstance){
                    for(int i=0;i<Library.list().size();i++) {
                        Library libraryInstance = Library.list().get(i)
                        libraryInstance.books.remove(bookInstance)
                    }
                    bookInstance.delete(flush: true)
                    render(status: 200, text: "Book deleted successfully")
                }
                else
                    render(status: 404,text: "Book not found")
                break
        }
    }


    def getListBooks(){
        def books=Library.get(params.libid).books

        if(books){
            withFormat {
                json {render books as JSON}
                xml {render books as XML}
            }
        }
        else{
            render(status: 201, text: "Library empty")
        }
    }

}
