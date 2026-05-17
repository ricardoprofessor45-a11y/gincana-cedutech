const API_URL = "";

let cadastroEmEdicaoId = null;
let listaCadastrosMemoria = [];

/* =========================
   SUBMIT (POST ou PUT)
========================= */
document.getElementById("cadastroForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const cadastro = {
        aluno: {
            nome: alunoNome.value.trim(),
            matricula: alunoMatricula.value.trim(),
            turma: alunoTurma.value.trim()
        },
        jovemConvidado: {
            nome: jovemNome.value.trim(),
            idade: parseInt(jovemIdade.value),
            periodo: jovemPeriodo.value,
            telefone: jovemTelefone.value.trim()
        },
        responsavel: {
            nome: respNome.value.trim(),
            telefone: respTelefone.value.trim()
        }
    };

    const method = cadastroEmEdicaoId ? "PUT" : "POST";
    const url = cadastroEmEdicaoId
        ? `${API_URL}/cadastros/${cadastroEmEdicaoId}`
        : `${API_URL}/cadastros`;

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cadastro)
    })
        .then(res => {
            if (!res.ok) throw new Error();
            alert(cadastroEmEdicaoId ? "Cadastro atualizado com sucesso!" : "Cadastro realizado com sucesso!");
            cadastroEmEdicaoId = null;
            cadastroForm.reset();
            carregarCadastros();
            carregarRanking();
        })
        .catch(() => alert("Erro ao salvar cadastro"));
});

/* =========================
   LISTAR CADASTROS
========================= */
function carregarCadastros() {
    fetch(`${API_URL}/cadastros`)
        .then(res => res.json())
        .then(dados => {
            listaCadastrosMemoria = dados || [];
            const tbody = document.getElementById("listaCadastros");
            tbody.innerHTML = "";

            if (listaCadastrosMemoria.length === 0) {
                tbody.innerHTML = `
                    <tr>
                        <td colspan="5">Nenhum cadastro realizado</td>
                    </tr>
                `;
                return;
            }

            listaCadastrosMemoria.forEach(c => {
                tbody.innerHTML += `
                    <tr>
                        <td>${c.aluno.nome}</td>
                        <td>${c.aluno.matricula}</td>
                        <td>${c.aluno.turma}</td>
                        <td>${c.jovemConvidado.nome}</td>
                        <td>
                            <button class="editar" onclick="editarCadastro(${c.id})">Editar</button>
                            <button class="excluir" onclick="excluirCadastro(${c.id})">Excluir</button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(() => {
            document.getElementById("listaCadastros").innerHTML = `
                <tr>
                    <td colspan="5">Erro ao carregar cadastros</td>
                </tr>
            `;
        });
}

/* =========================
   EDITAR CADASTRO
========================= */
function editarCadastro(id) {
    const cadastro = listaCadastrosMemoria.find(c => c.id === id);
    if (!cadastro) {
        alert("Cadastro não encontrado.");
        return;
    }

    cadastroEmEdicaoId = cadastro.id;

    alunoNome.value = cadastro.aluno.nome;
    alunoMatricula.value = cadastro.aluno.matricula;
    alunoTurma.value = cadastro.aluno.turma;

    jovemNome.value = cadastro.jovemConvidado.nome;
    jovemIdade.value = cadastro.jovemConvidado.idade;
    jovemPeriodo.value = cadastro.jovemConvidado.periodo;
    jovemTelefone.value = cadastro.jovemConvidado.telefone;

    respNome.value = cadastro.responsavel.nome;
    respTelefone.value = cadastro.responsavel.telefone;

    alert("Modo edição ativado. Altere os dados e clique em salvar.");
}

/* =========================
   DELETE
========================= */
function excluirCadastro(id) {

    const confirmar = confirm("Deseja realmente excluir este cadastro?");
    if (!confirmar) return;

    fetch(`${API_URL}/cadastros/${id}`, {
        method: "DELETE"
    })
        .then(res => {
            if (!res.ok) throw new Error();
            alert("Cadastro excluído com sucesso!");
            carregarCadastros();
            carregarRanking();
        })
        .catch(() => alert("Erro ao excluir cadastro"));
}

/* =========================
   RANKINGS
========================= */
function carregarRanking() {

    fetch(`${API_URL}/ranking/alunos`)
        .then(res => res.json())
        .then(dados => {
            const tbody = document.getElementById("rankingAlunos");
            tbody.innerHTML = "";

            dados.forEach(r => {
                tbody.innerHTML += `
                    <tr>
                        <td>${r.nomeAluno}</td>
                        <td>${r.matricula}</td>
                        <td>${r.turma}</td>
                        <td>${r.totalCadastros}</td>
                    </tr>
                `;
            });
        });

    fetch(`${API_URL}/ranking/turmas`)
        .then(res => res.json())
        .then(dados => {
            const tbody = document.getElementById("rankingTurmas");
            tbody.innerHTML = "";

            dados.forEach(r => {
                tbody.innerHTML += `
                    <tr>
                        <td>${r.turma}</td>
                        <td>${r.totalCadastros}</td>
                    </tr>
                `;
            });
        });

    fetch(`${API_URL}/ranking/vencedora`)
        .then(res => res.json())
        .then(dado => {
            document.getElementById("turmaVencedora").textContent =
                dado ? `🏆 Turma vencedora: ${dado.turma}` : "";
        });
}

/* =========================
   INIT
========================= */
carregarCadastros();
carregarRanking();
