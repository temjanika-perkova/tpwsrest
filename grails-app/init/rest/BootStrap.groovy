package rest

class BootStrap {

    def init = { servletContext ->
        Date d1, d2, d3, d4;
        d1 = new Date(2011,05,12)
        d2 = new Date(1990,01,21)
        d3 = new Date(2011,06,22)
        d4 = new Date(2017,10,23)
        def library01 = new Library(name: "Albert Camus", address: "19 Boulevard Gustave Chancel, 06603 Antibes", yearCreated: 2010).save(flush: true)
        def book1=new Book(name:"Les Fleurs du Mal",releaseDate: d1,author: "Charles Baudelaire", isbn:"786540", library: library01 ).save(flush:true)
        def book2=new Book(name:"La Peste",releaseDate: d2, author: "Albert Camus", isbn:"45678", library: library01 ).save(flush:true)
        def book3=new Book(name:"Voyage au bout de la nuit",releaseDate: d3,author: "L-F Celine", isbn:"456345", library: library01 ).save(flush:true)
        def book4=new Book(name:"L'ecume des jours",releaseDate:d4 ,author: "Boris Vian", isbn:"876765", library: library01 ).save(flush:true)
        library01.addToBooks(book1).save(flush:true)
        library01.addToBooks(book2).save(flush:true)
        library01.addToBooks(book3).save(flush:true)
        library01.addToBooks(book4).save(flush:true)
        def library02 = new Library(name: "Louis Nucera", address:"2 Place Yves Klein, 06300 Nice", yearCreated: 2005).save(flush: true)
        def book10=new Book(name:"Le petit Prince",releaseDate:d1 ,author: "Antony de S-E", isbn: "876765", library: library02).save(flush:true)
        def book20=new Book(name:"Germinal",releaseDate:d2 ,author: "Zola", isbn: "987098", library: library02).save(flush:true)
        def book30=new Book(name:"Les Miserables",releaseDate:d3 ,author: "Victor Hugo", isbn:"456765", library: library02 ).save(flush:true)
        def book40=new Book(name:"Le pere Goriot",releaseDate:d4 ,author: "Honore de Balzac", isbn:"345456", library: library02 ).save(flush:true)

        library02.addToBooks(book10).save(flush:true)
        library02.addToBooks(book20).save(flush:true)
        library02.addToBooks(book30).save(flush:true)
        library02.addToBooks(book40).save(flush:true)
        def library03 = new Library (name:"Chevalier Victor de Cessole", address:"65 Rue de France, 06050 Nice", yearCreated: 1995).save(flush: true)
        def book21=new Book(name:"A la recherche du temps perdu",releaseDate:d3 ,author: "Marcel Prust", isbn:"675654", library: library03 ).save(flush:true)
        def book22=new Book(name:"L'etranger",releaseDate:d2 ,author: "Albert Camus", isbn: "223443", library: library03 ).save(flush:true)
        def book23=new Book(name:"Les feuilles d'automne",releaseDate:d4 ,author: "Victor Hugo", isbn: "221689", library: library03 ).save(flush:true)
        def book24=new Book(name:"Un coeur faible",releaseDate:d1,author:"Fedor M. Dostoievsky", isbn: "467929", library: library03 ).save(flush:true)
        library03.addToBooks(book21).save(flush:true)
        library03.addToBooks(book22).save(flush:true)
        library03.addToBooks(book23).save(flush:true)
        library03.addToBooks(book24).save(flush:true)
    }
    def destroy = {
    }
}
