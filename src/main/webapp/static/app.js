/**
 * Created by Alexey-Ivanovskiy on 02.07.2016.
 */
function processPost(){
    var postIdEl = document.getElementById("postId");

    var xmlhttp = new XMLHttpRequest();
    var url = "/api/v1/post/" + postIdEl.value;
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            getAndDrawAllPosts();
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function getAndDrawAllPosts(){
    var xmlhttp = new XMLHttpRequest();
    var url = "/api/v1/post";
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var posts = JSON.parse(xmlhttp.responseText);
            drawPosts(posts)
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function drawPosts(posts) {
    var out = '<table class="table table-striped">' +
        '<thead><tr>'+
        '<th>id</th>' +
        '<th>user id</th>' +
        '<th>title</th>' +
        '<th>body</th>' +
        '</tr></thead><tbody>';
    var i;
    for(i = 0; i < posts.length; i++) {
        out +=

            '<tr>' +
            '<td>' + posts[i].id + '</td>' +
            '<td>' + posts[i].userId + '</td>' +
            '<td>' + posts[i].title + '</td>' +
            '<td>' + posts[i].body + '</td>' + '</tr>'

    }
    out += '</tbody></table>';
    document.getElementById("drawArea").innerHTML = out;
}