package rest

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/$controller/$libid/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/library/$id?"(controller: 'library', action: 'index')
        "/libraries"(controller: 'library', action: 'index')
        "/book/$id?"(controller: 'book', action: 'index')
        "/books"(controller: 'book', action: 'index')


        //ressources assoiciées
        "/library/$libid/book/$id?"(controller: 'library', action: 'gestionBook')
        "/library/$libid/books/"(controller: 'book', action: 'getListBooks')


    }
}
