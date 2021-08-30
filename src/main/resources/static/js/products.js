console.log("Is works")

let categoryUrl = '/products/fetch/';
let detailsUrl = '/products/details/';

// document.querySelectorAll(".item").forEach(a => a.addEventListener("click" , function (ev) {
//     console.log(this);
//     console.log("click")
//     ev.preventDefault();
// }))

// --------------------------------WORK----------------------

// $(document).ready(function () {
//     $(".item").each(function () {
//         $(this).on("click", function (e) {
//             let currId = $(this).parents().children("input").attr("id");
//             fetch(categoryUrl + currId)
//                 .then((resp) => resp.json())
//                 .then((json) => {
//                     json.forEach(p => {
//                         console.log(categoryUrl + currId);
//                         //     $("#category-menu-products").appendTo(`<div class="box-up">
//                         //                     <a th:href="@{/}">
//                         //                         <img class="img img-fluid" th:src="${p.imageUrl}"  alt="Bicycle">
//                         //                     </a>
//                         //                     <div class="img-info">
//                         //                         <figcaption>
//                         //                             <h4 class="tm-gallery-title" th:text="${p.name}">Sed varius
//                         //                                 turpis</h4>
//                         //                             <p class="tm-gallery-description" th:text="${p.description}">Nam in
//                         //                                 suscipit nisi, sit amet consectetur metus. Ut sit amet tellus
//                         //                                 accumsan</p>
//                         //                             <p class="tm-gallery-price" th:text="${p.price}">$30.50</p>
//                         //                         </figcaption>
//                         //                     </div>
//                         //                 </div>`)
//                         // })
//                         console.log(p)
//
//                     })
//                     // console.log($(this).parents().children("input").attr("id"));
//                     // console.log("clicked!");
//                     // console.log(this);
//                     e.preventDefault()
//                 })
//         })
//     })
// });

