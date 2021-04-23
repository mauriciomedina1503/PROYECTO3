package hn.ujcv.edu.p3.Res.service;

import hn.ujcv.edu.p3.Res.entity.Boletos;
import hn.ujcv.edu.p3.Res.exceptions.BusinessException;
import hn.ujcv.edu.p3.Res.exceptions.NotFoundException;
import hn.ujcv.edu.p3.Res.repository.BoletosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletosService implements IBoletoService{

    @Autowired
    private BoletosRepository repository;

    @Override
    public Boletos saveBoleto(Boletos boleto) throws BusinessException {
        try{
             return repository.save(boleto);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }//

    @Override
    public List<Boletos> saveBoletos(List<Boletos> boletos) throws BusinessException {
        try{
            return repository.saveAll(boletos);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }//

    @Override
    public List<Boletos> getBoletos() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }//

    @Override
    public Boletos getBoletoById(long id) throws BusinessException, NotFoundException {
        Optional<Boletos> opt = null;
        try{
            opt = repository.findById(id);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

        if(opt.isPresent()){
            throw new NotFoundException("No se encontró el boleto "+id);
        }
        return opt.get();
    }//

    @Override
    public Boletos getBoletoByIdUsuario(int idusuario) throws BusinessException, NotFoundException {
        Optional<Boletos> opt = null;
        try{
            opt = repository.findBoletoByidusuario(idusuario);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

        if(opt.isPresent()){
            throw new NotFoundException("No se encontró el boleto "+idusuario);
        }
        return opt.get();
    }//

    @Override
    public void deleteBoleto(long id) throws BusinessException, NotFoundException {
        Optional<Boletos> opt = null;
        try{
            opt = repository.findById(id);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

        if(!opt.isPresent()){
            throw new NotFoundException("No se encontró el boleto "+id);
        }
        else{
            try {
                    repository.deleteById(id);
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }//

    @Override
    public Boletos updateBoleto(Boletos boleto) throws BusinessException, NotFoundException {
        Optional<Boletos> opt = null;
        try{
            opt = repository.findById(boleto.getId());
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

        if(!opt.isPresent()){
            throw new NotFoundException("No se encontró el boleto "+boleto.getId());
        }
        else{
            try {
                Boletos existingBoleto = new Boletos();
                existingBoleto.setId(boleto.getId());
                existingBoleto.setIdusuario(boleto.getIdusuario());
                existingBoleto.setPrecio(boleto.getPrecio());
                existingBoleto.setCorreo(boleto.getCorreo());
                existingBoleto.setNumeroentrada(boleto.getNumeroentrada());
                existingBoleto.setFecha(boleto.getFecha());
                existingBoleto.setTelefono(boleto.getTelefono());
                existingBoleto.setEntradageneral(boleto.getEntradageneral());
                existingBoleto.setHoraevento(boleto.getHoraevento());
                existingBoleto.setVip(boleto.getVip());
                    return repository.save(existingBoleto);
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }//
}//
